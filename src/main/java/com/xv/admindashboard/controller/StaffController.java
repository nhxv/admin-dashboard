package com.xv.admindashboard.controller;

import com.xv.admindashboard.model.Staff;
import com.xv.admindashboard.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class StaffController {
    private StaffRepository staffRepository;

    @Autowired
    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/staffs")
    public List<Staff> getAllStaffs() {
        return this.staffRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/staffs/{id}")
    public Staff getStaff(@PathVariable(value = "id") Long staffId) throws Exception {
        return staffRepository.findById(staffId).orElseThrow(() -> new Exception("Staff not found " + staffId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/staffs")
    public Staff createStaff(@Valid @RequestBody Staff staff) {
        return this.staffRepository.save(staff);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/employees/{id}")
    public Staff updateStaff(@PathVariable(value = "id") Long staffId, @Valid @RequestBody Staff staffUpdate) throws Exception {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new Exception("Staff not found" + staffId));
        return this.staffRepository.save(staff);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteStaff(@PathVariable(value = "id") Long staffId) throws Exception {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new Exception("Staff not found" + staffId));
        this.staffRepository.delete(staff);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
