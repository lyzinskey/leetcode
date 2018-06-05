//There are a total of n courses you have to take, labeled from 0 to n-1.
//Some courses may have prerequisites,
//for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

//Example 1:
//
//Input: 2, [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take.
//To take course 1 you should have finished course 0. So it is possible.

//Example 2:
//
//Input: 2, [[1,0], [0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take.
//To take course 1 you should have finished course 0, and to take course 0 you should
//also have finished course 1. So it is impossible.

//Note:
//The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
//Read more about how a graph is represented. 
//(https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs)
//You may assume that there are no duplicate edges in the input prerequisites.


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] edges = new ArrayList[numCourses];
        int[] degree = new int[numCourses];
        int courseCounter = 0;

        //count degree of all the nodes, degree represents in-degree of every node here
        for (int i = 0; i < prerequisites.length; i++) {
            degree[prerequisites[i][0]]++;
        }

        //edges here is an array of ArrayList
        //elements in array represents courses,
        //the corresponding ArrayList represents all the courses you can take after you finish the current course
        for (int i = 0; i < numCourses; i++) {
            edges[i] = new ArrayList<>();
        }

        //add all edges
        for (int i = 0; i < prerequisites.length; i++) {
            edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        //if a course has no prerequisites (in-degree equals 0), add it to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0){
                queue.offer(i);
            }
        }

        //get all the courses which prerequisite is the current course, subtract their degree by 1
        //add all the courses to queue if their degree become 0
        while (!queue.isEmpty()){
            int course = queue.poll();
            int size = edges[course].size();
            courseCounter++;
            for (int i = 0; i < size; i++) {
                int nextCourse= edges[course].get(i);
                degree[nextCourse]--;
                if (degree[nextCourse] == 0){
                    queue.offer(nextCourse);
                }
            }
        }
        return courseCounter == numCourses;
    }
    
    
