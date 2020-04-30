package com.springfileupload.app.controller;

import com.springfileupload.app.model.FileStore;
import com.springfileupload.app.service.FileStoreService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileStoreController {

    private final FileStoreService fileStoreService;

    public FileStoreController(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("fileList", fileStoreService.getAllFiles());
        return "index";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam("file") MultipartFile file, ModelMap modelMap) {

        if (fileStoreService.saveFileToDB(file) != null && fileStoreService.saveFileToLocal(file)) {
            modelMap.addAttribute("msg", "file is uploaded Successfully");
        } else {
            modelMap.addAttribute("msg", "sorry!file is not uploaded");
        }

        modelMap.addAttribute("fileList", fileStoreService.getAllFiles());
        return "index";
    }


    @RequestMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("id") int id) {
        FileStore fileStore = fileStoreService.getFileStoreById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileStore.getFileName() + "\"")
                .body(new ByteArrayResource(fileStore.getFile()));
    }

    @RequestMapping("/delete")
    public String deleteFile(@RequestParam("id") int id, ModelMap map) {
        try {
            fileStoreService.deleteFileFromLocal(fileStoreService.getFileStoreById(id));
            fileStoreService.deleteFileFromDB(id);
            map.addAttribute("msg", "file is deleted");
            map.addAttribute("fileList", fileStoreService.getAllFiles());
        } catch (Exception exception) {
            map.addAttribute("$msg", "file is not deleted");
        }
        return "index";
    }

}
