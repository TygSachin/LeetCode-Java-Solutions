public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        // the key: how to utilize original map, instead of constructing map each time?
        // traverse the string, word by word, instead of char by char!!
        int wL = words[0].length();
        int dictL = wL * words.length;
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> dictMap = getMap(words);
        for (int i = 0; i < wL; ++i) {
            if (i + dictL <= s.length()) {
                Map<String, Integer> curMap = getMapFromString(s.substring(i, i + dictL), wL);
                if (dictMap.equals(curMap)) {
                    res.add(i);
                }
                for (int j = i + wL; j + dictL <= s.length(); j = j + wL) {
                    // update the map
                    // System.out.print(j + " ");
                    String prev = s.substring(j - wL, j);
                    curMap.put(prev, curMap.get(prev) - 1);
                    if (curMap.get(prev) == 0) curMap.remove(prev);
                    String last = s.substring(j + dictL - wL, j + dictL);
                    // previously written as curMap.containsKey(last) ? 0 : curMap.get(last) + 1;
                    // what the hell???!!!
                    curMap.put(last, !curMap.containsKey(last) ? 1 : curMap.get(last) + 1);
                    // check the map
                    if (dictMap.equals(curMap)) res.add(j);
                }
            }
            else break;
        }
        return res;
    }
    public Map<String, Integer> getMap(String[] words) {
        Map<String, Integer> hm = new HashMap<>();
        for (String s : words) hm.put(s, !hm.containsKey(s) ? 1 : hm.get(s) + 1);
        return hm;
    }
    public Map<String, Integer> getMapFromString(String s, int wL) {
        Map<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); i = i + wL) {
            String cW = s.substring(i, i + wL); 
            hm.put(cW, !hm.containsKey(cW) ? 1 : hm.get(cW) + 1);
        }
        return hm;
    }
}