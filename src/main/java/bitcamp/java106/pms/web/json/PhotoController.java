package bitcamp.java106.pms.web.json;

import java.io.File;
import java.util.List;
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
    public int add(String title, MultipartFile[] files) throws Exception {
        
        String filesDir = sc.getRealPath("/files");
        
        
        Photo[] photos = new Photo[files.length];
         
        for (int i = 0; i  < files.length; i++) {
            Photo photo = new Photo();
            photo.setTitle(title);
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
                            @RequestParam("preDate") String preDate) {  
        
       List<Photo> photos = photoService.listByDate(nowDate, preDate);
       try {           
           for(Photo photo : photos) {
               photo.setTitle(photo.getTitle());
           }
       }catch(Exception e){
           
       }
       return photos;
        
    }
    
    @RequestMapping("{no}")
    public Photo view(@PathVariable int no) throws Exception {
        System.out.println(no);
        System.out.println(photoService.listByOne(no));
        return photoService.listByOne(no);
    }
    
    @RequestMapping("delete{no}")
    public void delete(@PathVariable int no) throws Exception {
        System.out.println(no);
        photoService.delete(no);
    }
    
    
}
