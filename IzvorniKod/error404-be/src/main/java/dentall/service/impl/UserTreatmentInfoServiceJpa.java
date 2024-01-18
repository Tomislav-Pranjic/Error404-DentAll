package dentall.service.impl;

import dentall.Error404BeApplication;
import dentall.dao.AddressRepository;
import dentall.dao.MedUserRepository;
import dentall.dao.UserTreatmentInfoRepository;
import dentall.domain.Accommodation;
import dentall.domain.Address;
import dentall.domain.MedUser;
import dentall.domain.UserTreatmentInfo;
import dentall.rest.dto.TreatmentInfoPatchDTO;
import dentall.service.UserTreatmentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class UserTreatmentInfoServiceJpa implements UserTreatmentInfoService {

    @Autowired
    private UserTreatmentInfoRepository treatmentInfoRepository;

    @Autowired
    private MedUserRepository medUserRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Logger logger = LoggerFactory.getLogger(UserTreatmentInfoServiceJpa.class);

    @Override
    public List<UserTreatmentInfo> listAll() {
        return treatmentInfoRepository.findAll();
    }

    @Override
    public UserTreatmentInfo createTreatment(UserTreatmentInfo treatment) {
        return treatmentInfoRepository.save(treatment);
    }

    @Override
    public int countTreatmentsWithUserAndDate(Long userId, Date date) {
        MedUser user = medUserRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error while counting treatments(User not found)"));

        return treatmentInfoRepository.countUserTreatmentInfosByMedUserAndTreatmentDate(user, date);
    }

    @Override
    public List<UserTreatmentInfo> findAllByMissingArrivalAndDepartureInfo() {
        return treatmentInfoRepository.findAllByArrivalAddressIsNullOrArrivalDateIsNullOrDepartureAddressIsNullOrDepartureDateIsNull();
    }

    @Override
    public List<UserTreatmentInfo> findAllByMissingAccommodationAndDriverInfo() {
        return treatmentInfoRepository.findAllByAccommodationIsNullAndArrivalDriverIsNullAndDepartureDriverIsNull();
    }

    @Override
    public UserTreatmentInfo updateTreatment(UserTreatmentInfo treatment) {
        return treatmentInfoRepository.saveAndFlush(treatment);
    }

    @Override
    public boolean isAccommodationFreeBetweenDates(Accommodation accommodation, Date arrivalDate, Date departureDate) {
        int count = treatmentInfoRepository.countAllInfoForAccommodationBetweenDates(accommodation, arrivalDate, departureDate);
        if(count == 0) {
            return true;
        }else if(count == 1){
            return false;
        }else{
            logger.error("Error while checking if accommodation is free between dates. Count: " + count);
            for(UserTreatmentInfo info : treatmentInfoRepository.findAllInfoForAccommodationBetweenDates(accommodation, arrivalDate, departureDate)) {
                logger.error("Info for count: " + info);
            }
            return false;
        }
    }

    @Override
    public UserTreatmentInfo updateTreatmentInfo(Long id, TreatmentInfoPatchDTO dto) {
        UserTreatmentInfo treatment = treatmentInfoRepository.findById(id).orElseThrow(() -> new RuntimeException("Treatment info with id '" + id + "' does not exist."));

        DateFormat df = new SimpleDateFormat(Error404BeApplication.DATE_FORMAT);

        if(dto.getArrivalDate() != null){
            try{
                treatment.setArrivalDate(new Date(df.parse(dto.getArrivalDate()).getTime()));
            }catch(Exception e){
                logger.error("Error while parsing arrival date: " + dto.getArrivalDate());
            }
        }

        if(dto.getDepartureDate() != null){
            try{
                treatment.setDepartureDate(new Date(df.parse(dto.getDepartureDate()).getTime()));
            }catch(Exception e){
                logger.error("Error while parsing departure date: " + dto.getDepartureDate());
            }
        }

        if(dto.getArrivalAddressId() != null){
            Address address = addressRepository.findById(dto.getArrivalAddressId()).orElseThrow(() -> new RuntimeException("Address with id '" + dto.getArrivalAddressId() + "' does not exist."));
            treatment.setArrivalAddress(address);
        }

        if(dto.getDepartureAddressId() != null){
            Address address = addressRepository.findById(dto.getDepartureAddressId()).orElseThrow(() -> new RuntimeException("Address with id '" + dto.getDepartureAddressId() + "' does not exist."));
            treatment.setDepartureAddress(address);
        }

        return treatmentInfoRepository.saveAndFlush(treatment);
    }
}
