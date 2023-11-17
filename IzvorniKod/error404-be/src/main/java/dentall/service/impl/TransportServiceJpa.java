package dentall.service.impl;

import dentall.dao.TransportRepository;
import dentall.domain.Transport;
import dentall.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportServiceJpa implements TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public List<Transport> listAll(){
        return transportRepository.findAll();
    }

}
