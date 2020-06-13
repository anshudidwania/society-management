package ad.society.apartmentmanagement.controllers;

import ad.society.apartmentmanagement.exception.ApartmentException;
import ad.society.apartmentmanagement.modal.ApartmentSearchFilterVO;
import ad.society.apartmentmanagement.modal.ApartmentVO;
import ad.society.apartmentmanagement.modal.ResponseVO;
import ad.society.apartmentmanagement.service.ApartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/society")
public class ApartmentController {
    private final Logger logger = LoggerFactory.getLogger(ApartmentController.class);

    @Autowired
    private ApartmentService apartmentService;

    @PostMapping(path = "/create")
    public ResponseVO createApartment(@RequestBody ApartmentVO vo){
        try{
            String apartmentId = apartmentService.createApartment(vo);
            logger.debug("create apartment successfully", apartmentId);
            return buildResponse(apartmentId, null);
        }catch (Exception ex){
            logger.error("create apartment faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @GetMapping(path = "/{apartment-id}")
    public ResponseVO getApartmentDetails(@PathVariable ("apartment-id") String apartmentId) {
        try{
            ApartmentVO vo = apartmentService.getApartmentDetails(apartmentId);
            logger.debug("getApartmentDetails successfully", vo);
            return buildResponse(vo, null);
        }catch (Exception ex){
            logger.error("getApartmentDetails faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @GetMapping(path = "/name/{apartment-id}")
    public ResponseVO getApartmentName(@PathVariable ("apartment-id") String apartmentId) {
        try{
            String name = apartmentService.getApartmentName(apartmentId);
            logger.debug("getApartmentDetails successfully", name);
            return buildResponse(name, null);
        }catch (Exception ex){
            logger.error("getApartmentDetails faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @GetMapping(path = "/findAll")
    public ResponseVO getAllApartment(){
        try{
            List<ApartmentVO> ls = apartmentService.getAllApartment();
            logger.debug("getAllApartment successfully", ls);
            return buildResponse(ls, null);
        }catch (Exception ex){
            logger.error("getAllApartment faliure", ex);
            return buildResponse(null, ex);
        }
    }

    @PostMapping(path = "/find")
    public ResponseVO searchApartment(@RequestBody ApartmentSearchFilterVO filterVO){
        try{
            List<ApartmentVO> ls = apartmentService.searchApartment(filterVO);
            logger.debug("searchApartment successfully", ls);
            return buildResponse(ls, null);
        }catch (Exception ex){
            logger.error("searchApartment faliure", ex);
            return buildResponse(null, ex);
        }
    }

    private ResponseVO buildResponse(Object data, Exception error) {
        if (isNull(error)) {
            return ResponseVO.builder()
                    .success(true)
                    .data(data).build();
        }else{
            ApartmentException ex = ApartmentException.builder()
                    .errorCode("E200")
                    .description(error.getMessage() + error.getLocalizedMessage())
                    .error(error.fillInStackTrace()).build();
            return ResponseVO.builder()
                    .success(false)
                    .error(ex).build();
        }
    }

}