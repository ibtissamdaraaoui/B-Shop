package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Writer;
import ma.xproce.bshop.dao.repositories.BookRepository;
import ma.xproce.bshop.dao.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WriterService implements  WriterManager{
    @Autowired
    public WriterRepository writerRepository;


    @Override
    public Writer addWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    @Override
    public List<Writer> getallWriters() {
        return writerRepository.findAll();
    }
    @Override
    public Writer updateWriter(Writer writer){
        if(writer.getId() != null){return  writerRepository.save(writer);}
        else{return null;}
    }
    public Writer getWriterByName(String writerName) {
        return writerRepository.findByName(writerName);
    }
    public List<Writer> findWritersByIds(List<Integer> list){
        return writerRepository.findAllById(list);
    }
}
