package bitcamp.java106.pms.mail;

import java.util.Random;

public class TempKeyGenerator {
    private boolean lowerCheck;
    private int size;
    
    public String getKey(int size, boolean lowerCheck) {
        this.size = size;
        this.lowerCheck = lowerCheck;
        return init();
    }
    
    private String init() {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        int num = 0;
        
        do {
            num = r.nextInt(75)+48;
            
            if( (num>=48 && num<=57) || (num>=65 && num <=90) || (num>=97 && num<=122) ) {
                sb.append( (char) num);
            } else
                continue;
            
        } while(sb.length() < size);
        
        if(lowerCheck)
            return sb.toString().toLowerCase();

        System.out.println(sb.toString());
        return sb.toString();
    }
}
