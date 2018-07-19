package bitcamp.java106.pms.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HashTags implements Serializable {
 
    private static final long serialVersionUID = 1L;
    
    private String tag;
    private Study study;
}
