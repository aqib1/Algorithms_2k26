package revision.rizwan;


// start = hit -> [hot] -> [dot, lot]

// end = cog


import java.util.*;

public class Leetcode127 {
    private List<String> getEdges(List<String> wordList, String word) {
        return wordList.stream().filter(x -> rule(x, word)).toList();
    }

    private boolean rule(String w1, String w2) {
        if(w1.length() != w2.length())
            return false;

        int diff = 0;
        for(int i = 0; i < w1.length(); i++) {
            if(w1.charAt(i) != w2.charAt(i))
                diff++;
        }

        return diff == 1;
    }


    // hit -> queue -> !empty

    // ["hot","dot","dog","lot","log","cog"]
    // hit -> hot -> [dot -> [dog -> [cog]] -> lot -> log]
    // child -> parent
    public int getLadder(List<String> wordList, String startWord, String endWord) {

        var path = new ArrayList<String>();
        var edgeParentMap = new HashMap<String, String>();
        var queue = new ArrayDeque<String>();
        var visited = new HashSet<String>();
        queue.add(startWord);
        edgeParentMap.put(startWord, null);
        visited.add(startWord);

        while (!queue.isEmpty()) {
            var current = queue.remove();

            if(current.equals(endWord))
                break;

            var edges = getEdges(wordList, current);
            for(var edge : edges) {
                if(!visited.contains(edge)) {
                    queue.add(edge);
                    visited.add(edge);
                    edgeParentMap.put(edge, current);
                }
            }
        }

        if(!visited.contains(endWord))
            return 0;

        for(var end=endWord; end != null; end=edgeParentMap.get(end)) {
            path.add(end);
        }
        return path.size();
    }

    static void main() {
        var leet = new Leetcode127();
        leet.getLadder(List.of("hot","dot","dog","lot","log"), "hit", "cog");
        // hit -> hot ->
        //        hot -> dot, lot
        //                dot -> hot,
        //                      lot -> log
        //                         dog -> dot
        //                                cog -> dog

        // cog -> dog -> dot -> hot -> hit -> null
    }
}
