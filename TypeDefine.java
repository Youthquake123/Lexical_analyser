import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TypeDefine {
    private final String keyWords[] = { "abstract", "boolean", "break", "byte",
            "case", "catch", "char", "class", "continue", "default", "do",
            "double", "else", "extends", "final", "finally", "float", "for",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try",
            "void", "while","strictfp","enum" }; // Main keywords
    private final char operators[] = { '+', '-', '*', '/', '=', '>', '<', '&', '|', '!' }; // Main operators


    private final char separators[] = { ',', ';', '{', '}', '(', ')', '[', ']', '_',
            ':', '.', '"','\\'}; // Some symbols

    private final String operatorsType2[] ={"!=","<=",">=","==","++","--","+=","-=","*=","/="};

    private Set<String> keyWordsSet =new HashSet<String>(Arrays.asList(keyWords));

    private Set<String>  operatorsType2Set = new HashSet<String>(Arrays.asList(operatorsType2));

    public boolean isOperatorsType2(String s){
        return operatorsType2Set.contains(s);
    }

    public   boolean isLetter(char ch){
        return Character.isLetter(ch);
    }

    public boolean isDigit(char ch){
        return Character.isDigit(ch);
    }

    /**
     * To make judgement whether it's keyword
     * @param s
     * @return boolean
     */
    public boolean isKeyWord(String s) {
        return keyWordsSet.contains(s);
    }

    /**
     * To make judgement whether it's operator
     * @param ch
     * @return boolean
     */
    public boolean isOperator(char ch) {
        for(char c:operators) {
            if (c == ch)
                return true;
        }
        return false;
    }

    /**
     * To make judgement whether it's separator
     * @param ch
     * @return boolean
     */
    public boolean isSeparators(char ch) {
        for(char c:separators){
            if(c==ch)
                return true;
        }
        return false;
    }


    public int getType(String str){
        int type = -1;
        Field[] fields = KeyTypes.class.getDeclaredFields();
        for(Field field: fields){
            if(field.getName().equals(str.toUpperCase())){
                try {
                    type = (Integer) field.get(new KeyTypes());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return type;
    }

}
