package catoNetworks;

public class Trie {
    private static final int LIMIT = 256;
    private final Trie[] nodes;
    private int count;
    private int end;
    public Trie() {
        this.nodes = new Trie[LIMIT];
    }

    public void insert(String word) {
        var current = this;
        for(var ch: word.toCharArray()) {
            if(current.nodes[ch] == null) {
                current.nodes[ch] = new Trie();
            }
            current = current.nodes[ch];
            current.count++;
        }
        current.end++;
    }

    public boolean contains(char ch) {
        return this.nodes[ch] != null;
    }

    public boolean contains(String word) {
        var curr = this;
        for(var ch: word.toCharArray()) {
            if(curr.nodes[ch] == null)
                return false;
            curr = curr.nodes[ch];
        }
        return curr.end > 0;
    }

    public boolean startsWith(String word) {
        var curr = this;
        for(char ch: word.toCharArray()) {
            if(curr.nodes[ch] == null)
                return false;
            curr = curr.nodes[ch];
        }
        return curr.count > 0;
    }


    void main() {
        var trie = new Trie();
        var sentence = "This is a book";
        var key = "book";
        for(var s: sentence.split(" ")) {
            trie.insert(s);
        }
        IO.println(trie.contains(key));
        IO.println(trie.startsWith("AThi"));
    }
}
