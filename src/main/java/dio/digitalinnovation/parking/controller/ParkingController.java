package dio.digitalinnovation.parking.controller;

import dio.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import dio.digitalinnovation.parking.controller.dto.ParkingDTO;
import dio.digitalinnovation.parking.controller.mapper.ParkingMapper;
import dio.digitalinnovation.parking.model.Parking;
import dio.digitalinnovation.parking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api(tags = "Parking Controller")
public class ParkingController {

    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;


    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @ApiOperation("Show All parked cars")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @ApiOperation("Show one parked car from id")
    public ResponseEntity<ParkingDTO> findById(String id) {
        Parking parking = parkingService.findById(id);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Set a new parked car")
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto) {
        Parking parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete a parked car")
    public ResponseEntity delete (@PathVariable String id) {
        parkingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Change data of a parked car")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id, @RequestBody ParkingCreateDTO dto) {
        Parking parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking = parkingService.update(id, parkingCreate);
        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    @ApiOperation("Checkout")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id) {
        Parking parking = parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

}
