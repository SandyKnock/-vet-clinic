package su.vistar.vetclinic.service.mongo;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import su.vistar.vetclinic.dao.mongo.FileStorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Владислав on 10.03.2017.
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {


    private FileStorageDao dao;


    public FileStorageDao getDao() {
        return dao;
    }

    @Autowired
    public void setDao(FileStorageDao dao) {
        this.dao = dao;
    }


    @Override
    public String store(InputStream inputStream, String fileName, String contentType, DBObject metaData) {
        return dao.store(inputStream, fileName, contentType, metaData);
    }

    @Override
    public GridFSDBFile retrive(String fileName) {
        return dao.retrive(fileName);
    }

    @Override
    public GridFSDBFile getById(String id) {
        return dao.getById(id);
    }

    @Override
    public GridFSDBFile getByFilename(String filename) {
        return dao.getByFilename(filename);
    }

    @Override
    public List findAll() {
        return dao.findAll();
    }

}
