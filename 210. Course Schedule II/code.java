//There are a total of n courses you have to take, labeled from 0 to n-1.

//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
//which is expressed as a pair: [0,1]

//Given the total number of courses and a list of prerequisite pairs, 
//return the ordering of courses you should take to finish all courses.

//There may be multiple correct orders, you just need to return one of them. 
//If it is impossible to finish all courses, return an empty array.

//Example 1:
//
//Input: 2, [[1,0]] 
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
//             course 0. So the correct course order is [0,1] .

//Example 2:
//
//Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,1,2,3] or [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
//             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
//             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .

//Note:
//
//The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
//Read more about how a graph is represented.
//(https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs)
//You may assume that there are no duplicate edges in the input prerequisites.


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0){
            int[] output = new int[numCourses];
            for (int i = 0; i < numCourses; i++){
                output[i] = i;
            }
            return output;
        }

        List<Integer>[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        ArrayList<Integer> result = new ArrayList<>();
        int[] output = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0){
                queue.offer(i);
                result.add(i);
            }
        }

        while (!queue.isEmpty()){
            int course = queue.poll();
            int size = edges[course].size();
            for (int i = 0; i < size; i++) {
                int nextCourse = edges[course].get(i);
                degree[nextCourse]--;
                if (degree[nextCourse] == 0){
                    queue.offer(nextCourse);
                    result.add(nextCourse);
                }
            }
        }
        
        if (result.size() < numCourses){
            return new int[] {};
        }
        else {
            for (int i = 0; i < result.size(); i++) {
                output[i] = result.get(i);
            }
            return output;
        }
    }
    
    
