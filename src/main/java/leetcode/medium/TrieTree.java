package leetcode.medium;

class TrieTree {

    static int max_int = 26;
    Node root;

    public static class Node {
        Node[] children;
        boolean isEnd;
        public Node(){
            this.children = new Node[max_int];
        }
    }

    /** Initialize your data structure here. */
    public TrieTree() {
        this.root = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node that = this.root;
        char[] chars = word.toCharArray();
        for(int i =0;i<chars.length;i++){
            Node n = that.children[chars[i]-'a'];
            if(n == null) n = new Node();
            that.children[chars[i]-'a'] = n;
            if(i == chars.length - 1){
                n.isEnd = true;
            }else{
                that = n;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node that = this.root;
        char[] chars = word.toCharArray();
        for(int i =0;i<chars.length;i++){
            Node n = that.children[chars[i]-'a'];
            if(n == null){
                return false;
            }

            if(i == chars.length - 1){
                return n.isEnd;
            }else{
                that = n;
            }
        }

        return false;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node that = this.root;
        char[] chars = prefix.toCharArray();
        for(int i =0;i<chars.length;i++){
            Node n = that.children[chars[i]-'a'];
            if(n == null){
                return false;
            }
            that = n;
        }

        return true;
    }

    public static void main(String[] args) {
        final TrieTree trieTree = new TrieTree();
        String key = "app";
        trieTree.insert(key);
        trieTree.insert("apple");
        trieTree.insert("beer");
        trieTree.insert("apps");
        System.out.println(trieTree.search(key));
    }
}
