package dentall.service.impl;

import dentall.dao.AccommodationTypeRepository;
import dentall.domain.AccommodationType;
import dentall.service.AccommodationTypeService;
import dentall.service.exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccommodationTypeServiceJpa implements AccommodationTypeService {

    @Autowired
    private AccommodationTypeRepository accommodationTypeRepo;

    @Override
    public List<AccommodationType> listAll() {
        return accommodationTypeRepo.findAll();
    }

    @Override
    public Optional<AccommodationType> findById(Long id) {
        return accommodationTypeRepo.findById(id);
    }

    @Override
    public AccommodationType createAccommodationType(String typeName, Integer typeSize) {
        Assert.hasText(typeName, "Name must be set.");
        Assert.isTrue(typeName.length() <= 32, "Name must be at most 32 characters.");

        Assert.notNull(typeSize, "Size must be set.");
        Assert.isTrue(typeSize > 0, "Size must be positive.");

        checkIfAccommodationTypeAlreadyExists(typeName, typeSize);

        AccommodationType accommodationType = new AccommodationType(typeName, typeSize);
        return accommodationTypeRepo.save(accommodationType);
    }

    @Override
    public AccommodationType updateAccommodationType(Long id, String name, Integer size) {
        AccommodationType accommodationType = accommodationTypeRepo.findById(id)
                .orElseThrow(() ->
                        new ItemNotFoundException("Accommodation type with id '" + id + "' not found")
                );

        if(name != null) {
            Assert.hasText(name, "Name must be set.");
            Assert.isTrue(name.length() <= 32, "Name must be at most 32 characters.");

            checkIfAccommodationTypeAlreadyExists(name, accommodationType.getTypeSize());

            accommodationType.setTypeName(name);
        }

        if(size != null) {
            Assert.isTrue(size > 0, "Size must be positive.");

            checkIfAccommodationTypeAlreadyExists(accommodationType.getTypeName(), size);

            accommodationType.setTypeSize(size);
        }

        return accommodationTypeRepo.save(accommodationType);
    }

    private void checkIfAccommodationTypeAlreadyExists(String name, Integer size) {
        Optional<AccommodationType> accommodationType = accommodationTypeRepo.findByTypeNameAndTypeSize(name, size);
        if (accommodationType.isPresent()) {
            throw new IllegalArgumentException("Accommodation type with name '" + name + "' and size '" + size + "' already exists.");
        }
    }
}
