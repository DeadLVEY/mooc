package Questions;

/**
 * 给定一个字符串s，计算输出含有连续两个s作为子串的最短字符串
 * abc -> abcabc，aba -> ababa，aaa -> aaaa，abcdab -> abcdabcdab
 */
public class String_repeat {
    public static String MergeString(String str) {
        String result = null;

        //1.为空返回null
        if (str == null || str == "") {
            return null;
        }
        //2.单个字符"a"->"aa"
        if(str.length()==1){
            return  str+str;
        }
        //3.只由同一个字符组成    "aaa"->"aaaa"
        boolean flag = true;
        char only = str.charAt(0);
        for(char c:str.toCharArray()){
            if(only != c){
                flag = false;
                break;
            }
        }
        if(flag){
            return str + only;
        }


        int len = str.length();
        for (int i = len-1; i >= 0; i--) {
            //用相同字符串从后往前匹配最长重复字符串     substring前闭后开，包含beginIndex
            String prefix = str.substring(0, i);
            if (str.endsWith(prefix)) {
                if (i != 0) {
                    //4.一般情况    "abca"->"abcabca"
                    return str + str.substring(i);
                } else {
                    //5.完全没有重复字符串   "abcb"->"abcbabcb"
                    result = str + str;
                }
            }
        }
        return result;
    }

}
