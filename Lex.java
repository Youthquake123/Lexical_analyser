import java.util.Locale;

public class Lex extends TypeDefine {
    private StringBuffer buffer = new StringBuffer();
    private int index = 0;
    private int syn =-2;
    private char ch;
    private String strToken="";

    public  Lex(String path){
        FileProcess.readFile(buffer,path);// Read the input file
//        System.out.println(buffer.length());
    }

    public void getChar(){
        if(index ==buffer.length()) {
            index = buffer.length() + 1;
            return;
        }
        ch = buffer.charAt(index);// ch is the first element of buffer
        index++;
    }

    public void skipBlank(){
        while(Character.isWhitespace(ch))
            getChar();
    }

    public void retract(){
        index--;
        ch=' ';
    }

    public void concat(){
        strToken = strToken+ch;
    }


    public  void writeToFile(String token,String txtConent){
        // System.out.println(token);
        int num = getType(token.toUpperCase());
        token = "("+token+", "+txtConent+")"+"\r\n";
        FileProcess.writeFile(token);
    }


    //DFA

    private void Scanner(){

        getChar();
        skipBlank();
        //System.out.println(ch+" "+index);

        if(index == buffer.length()+1) {
            syn = 100;
            return;
        }
        strToken="";

        //start with letter
        if(isLetter(ch)) {

            while(isLetter(ch)||isDigit(ch)){
                concat();
                getChar();
            }
            retract();

            syn = getType(strToken);

            if(isKeyWord(strToken)) {
                return;
            }

            if (syn == -1 && strToken.length() == 1)
                syn = 71;

        }
        //digit
        else if(isDigit(ch)){

            while(isDigit(ch)){
                // System.out.println(ch);
                concat();
                getChar();
            }

            if(ch =='.') {
                concat();
                getChar();


                while (isDigit(ch)) {
                    concat();
                    getChar();
                }

            }



            if(!isLetter(ch)){
                //System.out.println(ch);
                retract();
                syn = 0;
            }
            else
                syn =-1;

        }

        //operator
        else if(isOperator(ch)){
            if(ch=='/'){
                getChar();
                if(ch=='*') {
                    while (true) {
                        getChar();
                        if (ch == '*') {
                            getChar();
                            if (ch == '/') {
                                getChar();
                                break;
                            }
                        }
                    }

                }

                if(ch=='/'){
                    while(ch !=9) {
                        getChar();
                    }
                }
                retract();
            }

//            {"!=","<=",">=","==","++","--","+=","-=","*=","/="};

            switch(ch){
                case '+': {
                    concat();
                    syn = 51;

                    getChar();
                    if(ch=='=') {
                        syn = 87;
                        concat();
                        break;
                    }
                    else if(ch=='+'){
                        syn = 85;
                        concat();
                        break;
                    }

                    retract();
                    break;
                }
                case '-': {
                    concat();

                    getChar();
                    if(ch=='=') {
                        syn = 88;
                        concat();
                        break;
                    }
                    else if(ch=='-'){
                        syn = 86;
                        concat();
                        break;
                    }
                    retract();

                    syn = 52;
                    break;
                }
                case '*':{
                    concat();
//System.out.println('*');
                    getChar();
//System.out.println(ch);
                    if(ch =='='){
                        syn = 89;
                        concat();
                        break;
                    }
                    else
                        retract();


                    syn = 53;
                    break;
                }
                case '/': {
                    concat();
                    getChar();
                    if(ch =='='){
                        syn = 90;
                        concat();
                        break;
                    }
                    retract();

                    syn = 54;
                    break;
                }
                case '>': {
                    concat();
                    getChar();
                    if(ch =='='){
                        syn = 83;
                        concat();
                        break;
                    }
                    retract();
                    syn = 60;
                    break;
                }

                case '<': {
                    concat();
                    getChar();
                    if(ch =='='){
                        syn = 82;
                        concat();
                        break;
                    }
                    retract();
                    syn = 59;
                    break;
                }
                case '=': {
                    concat();
                    getChar();
                    if(ch =='='){
                        syn = 84;
                        concat();
                        break;
                    }
                    retract();
                    syn = 58;

                    break;
                }
                case '&': {
                    concat();
                    getChar();
                    if(ch =='&'){
                        syn = 91;
                        concat();
                        break;
                    }
                    retract();

                    syn = 55;
                    break;
                }
                case '|': {
                    concat();
                    getChar();
                    if(ch =='|'){
                        syn = 92;
                        concat();
                        break;
                    }
                    retract();
                    syn = 56;
                    break;
                }
                case '!': {
                    concat();
                    getChar();
                    if(ch =='='){
                        syn = 81;
                        concat();
                        break;
                    }
                    retract();
                    syn = 57;
                    break;
                }


                default:  break;
            }

        }

        else if(isSeparators(ch)){
            // System.out.println("separators");
            concat();
            //  System.out.println(strToken);
            syn = 61;

        }

        else {
            concat();
            syn = -1;
        }

    }

    public void lexicalAnalyse(){

        FileProcess.clearFile();
        while(syn !=100){
            Scanner();
            // Identifiers
            if(syn==-1)
                writeToFile("error",strToken);

            if(syn==71)
                writeToFile("id",strToken);
            // Keywords
            if(syn>=1 &&syn<= 50)
                writeToFile(strToken,strToken);
            // Numbers
            if(syn==0)
                writeToFile("digit",strToken);
            // Seperators
            if(syn==61&&!strToken.equals(""))
                writeToFile("separators",strToken);

            if(syn>=81&&syn<=92) {
                writeToFile("op", strToken);
                // System.out.println(strToken);
            }

            if(syn>=51&&syn<=60)
            {
                switch(syn){
                    case 51: writeToFile("plus",'+'+""); break;
                    case 52: writeToFile("min",'-'+""); break;
                    case 53: writeToFile("mul",'*'+""); break;
                    case 54: writeToFile("div",'/'+""); break;
                    case 60: writeToFile("gt",'>'+""); break;
                    case 59: writeToFile("lt",'<'+""); break;
                    case 58: writeToFile("eq",'='+""); break;
                    case 55: writeToFile("and",'&'+""); break;
                    case 56: writeToFile("or",'|'+""); break;
                    case 57: writeToFile("not",'!'+""); break;
                    default:  break;
                }
            }
        }
    }
}
