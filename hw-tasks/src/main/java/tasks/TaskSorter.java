
package tasks;

import graph.*;

import java.util.*;

/**
 * A TaskSorter is a class that can store tasks and dependencies between them,
 * as well as order the tasks in a way such that each task appears before any
 * other tasks that it depends on (assuming that is possible).
 */
public class TaskSorter {

    private Graph<Task, Dependency> td;
    // nodes of Graphs should be Task objects,
    // and edges should be Dependency objects.
    // You don't have to write an abstraction function or
    // representation invariant for this class.

    /**
     * Creates a new TaskSorter object with no added tasks or dependencies.
     */
    public TaskSorter() {
        td = new Graph<>();
    }

    /**
     * Adds a new Task to the TaskSorter. If the task is already included, then
     * this will do nothing.
     *
     * @param t Task to be added
     * @spec.requires t != null
     */
    public void addTask(Task t) {
        // TODO: Implement adding a Task as a node.
        //       Do nothing if the task exists already.
        if(!td.nodes().contains(t)) {
            td.addNode(td.new GraphNode(t));
        }
    }

    /**
     * Returns a set containing all tasks in the sorter
     * @return A set of all tasks in this TaskSorter.
     */
    public Set<Task> getTasks() {
        // TODO: Implement getting all the tasks (nodes) in the graph.
        Set<Task> tasks = new HashSet<>();
        List<Graph<Task, Dependency>.GraphNode> nodes = td.nodes();
        for(Graph<Task, Dependency>.GraphNode node : nodes){
            tasks.add(node.getData());
        }
        return tasks;
    }

    /**
     * Adds a new Dependency to the TaskSorter. If the dependency is already
     * added, then this will do nothing.
     *
     * @param dep Dependency to be added
     * @spec.requires dep != null and before and after tasks exist for dep
     */
    public void addDependency(Dependency dep){
        Task before = dep.getBeforeTask();
        Task after = dep.getAfterTask();

        Graph<Task, Dependency>.GraphEdge newDep = td.new GraphEdge(td.getNode(before), td.getNode(after), dep);
        td.addEdge(newDep);

        // TODO: Implement adding a Dependency as an edge.
        //       Do nothing if the same dependency exists already.
        // NOTE: The edge should go from "before" to "after"!
        //       The tests will not pass if the edges are the other way.
    }

    /**
     * Given a Task, finds the dependencies that have the given task as a
     * prerequisite (the "before" task of the dependency).
     *
     * @param t the task to search for in the dependencies
     * @spec.requires t != null and t has already been added as a task
     * @return set of dependencies with {@code t} as the "before" task
     */
    public Set<Dependency> getOutgoingDependencies(Task t){
        // TODO: Implement getting the dependencies that point to the tasks
        //       depending on the given Task (in other words, get the edges
        //       to a node's children in the graph)

        Graph<Task, Dependency>.GraphNode task = td.getNode(t);
        List<Graph<Task, Dependency>.GraphEdge> outgoingEdges = task.getEdges();
        Set<Dependency> outgoingDependencies = new HashSet<>();

        for(Graph<Task, Dependency>.GraphEdge e : outgoingEdges){
            outgoingDependencies.add(e.getData());
        }

        return outgoingDependencies;
    }

    /**
     * Returns a list of the dependencies in an order that has the "before"
     * task of any dependency appearing before the "after" task.  If multiple
     * orders are possible, this method prioritizes tasks with names that are 
     * alphabetically earlier. If there is a cycle of dependencies, meaning
     * that no such ordering is possible, then this returns null.
     *
     * @return List of all tasks that respects the ordering requirements of the
     *     dependencies or null if no such ordering is possible.
     */
    public List<Task> sortTasks(){
        List<Task> tasks = new ArrayList<>(getTasks());
        Set<Task> visited = new HashSet<>();
        Stack<Task> taskStack = new Stack<>();
        tasks.sort(Comparator.comparing(Task::name).reversed());
        for (Task task : tasks) {
            if (dfs(task, visited, taskStack)) {
                return null;
            }
        }
        List<Task> sortedTasks = new ArrayList<>();
        while (!taskStack.isEmpty()) {
            sortedTasks.add(taskStack.pop());
        }
        return sortedTasks;
    }

    // Helper function for sortTasks, given a "start" task, set of visited tasks, adds to a stack
    // of tasks in reverse topological order (https://en.wikipedia.org/wiki/Topological_sorting) if such
    // an order is possible. If different Tasks can be done in any order and still satisfy the dependencies,
    // addChain first adds the Tasks with alphabetically later names. If a cycle is detected in
    // the dependencies, than returns true, otherwise returns false.
    private boolean dfs(Task start, Set<Task> visited, Stack<Task> taskStack){
        Set<Task> localVisited = new HashSet<>();
        Stack<Task> dfsStack = new Stack<>();
        Stack<Task> recordStack = new Stack<>();
        dfsStack.push(start);
        while (!dfsStack.isEmpty()) {
            Task current = dfsStack.pop();
            if (!visited.contains(current)) {
                localVisited.add(current);
                visited.add(current);
                recordStack.push(current);
                List<Dependency> deps = new ArrayList<>(getOutgoingDependencies(current));
                deps.sort(new Comparator<Dependency>() {
                    @Override
                    public int compare(Dependency t1, Dependency t2) {
                        return t2.getAfterTask().name().compareTo(t1.getAfterTask().name());
                    }
                });
                for (Dependency nextDep : deps) {
                    Task next = nextDep.getAfterTask();
                    if (localVisited.contains(next)) {
                        return true;
                    }
                    dfsStack.push(next);
                }
            }
        }
        while (!recordStack.isEmpty()) {
            taskStack.push(recordStack.pop());
        }
        return false;
    }
}
