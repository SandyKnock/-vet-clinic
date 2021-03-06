package su.vistar.vetclinic.service.mongo;

import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Владислав on 10.03.2017.
 */
public interface FileStorageService {
    String store(InputStream inputStream, String fileName,
                        String contentType, DBObject metaData);

    GridFSDBFile retrive(String fileName);

    GridFSDBFile getById(String id);

    GridFSDBFile getByFilename(String filename);

    List findAll();

}
