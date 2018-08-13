package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java106.pms.domain.Photo;
import bitcamp.java106.pms.service.PhotoService;
import net.coobird.thumbnailator.Thumbnails;

@RestController
@RequestMapping("/photo")
public class PhotoController {
    
    @Autowired ServletContext sc;
    PhotoService photoService;
    
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }
    
    @RequestMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public int add(String title, int memNo, int studyNo, MultipartFile[] files) throws Exception {
        
        String filesDir = sc.getRealPath("/files");
        System.out.println(memNo);
        System.out.println(studyNo);
        
        Photo[] photos = new Photo[files.length];
         
        for (int i = 0; i  < files.length; i++) {
            Photo photo = new Photo();
            photo.setTitle(title);
            photo.setMemNo(memNo);
            photo.setStudyNo(studyNo);
            String filename = UUID.randomUUID().toString();
            photo.setPhoto(filename);
            
            try {
                File path = new File(filesDir + "/" + filename);
                files[i].transferTo(path);
                
                String mainthumPho = path.getCanonicalPath() + "_350x350";
                Thumbnails.of(path)
                .size(350, 350)
                .outputFormat("jpg")
                .toFile(mainthumPho);
                
                String onethumPho = path.getCanonicalPath() + "_380x380";
                Thumbnails.of(path)
                .size(380, 380)
                .outputFormat("jpg")
                .toFile(onethumPho);
                
                 
            } catch (Exception e) {
                e.printStackTrace();
            }
            photos[i] = photo;
            
        }
        return photoService.insert(photos);
          
    }
    
    @RequestMapping("list")
    public List<Photo> list(@RequestParam("nowDate") String nowDate,
                            @RequestParam("preDate") String preDate,
                            @RequestParam("studyNo") String studyNo) {  
        System.out.println(studyNo);
       List<Photo> photos = photoService.listByDate(nowDate, preDate, studyNo);
       try {           
           for(Photo photo : photos) {
               photo.setTitle(photo.getTitle());
           }
       }catch(Exception e){
           return null;
           
       }
       return photos;
    }
    
    @RequestMapping("{no}")
    public Photo view(@PathVariable int no,
                      @RequestParam("studyNo") String studyNo) throws Exception {
        Map<String,Object> params = new HashMap<>();
        params.put("no", no);
        params.put("studyNo", studyNo);
        System.out.println(photoService.listByOne(params));
        return photoService.listByOne(params);
    }
    
    @RequestMapping("delete{no}")
    public void delete(@PathVariable int no) throws Exception {
        
        photoService.delete(no);
    }
    
    @RequestMapping("groupList")
    public List<Photo> view(@RequestParam("studyNo") String studyNo) throws Exception {
        System.out.println(photoService.listByGroup(studyNo));
        System.out.println(studyNo);
        return photoService.listByGroup(studyNo);
    }
    
}
