package ma.xproce.bshop.service;

import ma.xproce.bshop.dao.entities.Book;
import ma.xproce.bshop.dao.entities.Writer;

import java.util.List;

public interface WriterManager {
    public Writer addWriter(Writer writer);
    public List<Writer> getallWriters();
    public Writer updateWriter(Writer writer);
    public Writer getWriterByName(String writerName);

    List<Writer> findWritersByIds(List<Integer> list);
}
