//Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. 
//The org sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 10^4. 
//Reconstruction means building a shortest common supersequence of the sequences in seqs 
//(i.e., a shortest sequence so that all sequences in seqs are subsequences of it). 
//Determine whether there is only one sequence that can be reconstructed from seqs and it is the org sequence.

//Example 1:
//
//Input:
//org: [1,2,3], seqs: [[1,2],[1,3]]
//
//Output:
//false
//
//Explanation:
//[1,2,3] is not the only one sequence that can be reconstructed, 
//because [1,3,2] is also a valid sequence that can be reconstructed.

//Example 2:
//
//Input:
//org: [1,2,3], seqs: [[1,2]]
//
//Output:
//false
//
//Explanation:
//The reconstructed sequence can only be [1,2].

//Example 3:
//
//Input:
//org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
//
//Output:
//true
//
//Explanation:
//The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].

//Example 4:
//
//Input:
//org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
//
//Output:
//true



    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        //map用来存储edge
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
        //indegree用来存储每个点的入度
        Map<Integer, Integer> indegree = new HashMap<Integer, Integer>();

        //对org中对每个点进行初始化
        //在map中给对应的点添加HashSet以存储该点出发的所有edge
        //在indegree中将每个点的入度初始化为0
        for (int num : org) {
            map.put(num, new HashSet<Integer>());
            indegree.put(num, 0);
        }

        //题目要求org是满足 "a permutation of the integers from 1 to n" 的数组
        //因此org中数字上限是org.length
        int n = org.length;

        //count表示seqs中所有数组的长度和
        int count = 0;

        //把所有edge存储进maps
        //计算每个点的入度并存储进indegree
        for (List<Integer> seq : seqs) {
            count += seq.size();

            //因为下面的for循环要从i = 1开始（使用了seq[i - 1]）
            //故每个seq中的第一个元素需要单独判断
            if (seq.size() >= 1 && (seq.get(0) <= 0 || seq.get(0) > n))
                return false;
            for (int i = 1; i < seq.size(); i++) {
                if (seq.get(i) <= 0 || seq.get(i) > n)
                    return false;

                //数字b紧跟在数字a后，表示有一条从a到b的edge
                //将这条边存储进map，同时将b的入度+1
                if (map.get(seq.get(i - 1)).add(seq.get(i)))
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
            }
        }

        //当count小于org。length时，结果必然为false
        //因为想要表示n个数字的先后关系至少要用n个数字来表示（即把原数组重复一遍）
        // case: [1], []
        if (count < n)
            return false;

        //这里Queue用ArrayDeque或者LinkedList都是可以的
        //使用ArrayDeque的原因是它比LinkedList更快
        //http://www.baeldung.com/java-array-deque
        //https://gist.github.com/psayre23/c30a821239f4818b0709
        Queue<Integer> queue = new ArrayDeque<Integer>();

        //使用keySet来遍历indegree，并把所有入度为0的点加到queue中
        //https://blog.csdn.net/windlyb/article/details/5749884
        //https://blog.csdn.net/liu826710/article/details/9001254
        for (int key : indegree.keySet())
            if (indegree.get(key) == 0)
                queue.add(key);

        int cnt = 0;

        //只有当每个点有唯一确定的下一个点时我们才能重构数组
        //给定 org = [1,2,3], seqs = [[1,2],[1,3]]
        //返回 false
        //[1,2,3] 并不是唯一可以被重构出的序列，还可以重构出 [1,3,2]
        //因此这里while循环点判断条件并不是 "!queue.isEmpty()"
        //我们需要的是唯一性，而不是把当前点所连接的下一个点全找出来
        //所以这里循环条件是queue.size() == 1
        while (queue.size() == 1) {
            int ele = queue.poll();
            for (int next : map.get(ele)) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.add(next);
                }
            }
            if (ele != org[cnt]) {
                return false;
            }
            cnt++;
        }

        return cnt == org.length;
    }
    
    
    
