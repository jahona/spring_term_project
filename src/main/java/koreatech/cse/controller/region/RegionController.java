package koreatech.cse.controller.region;

import koreatech.cse.domain.region.ReSearchable;
import koreatech.cse.domain.region.RegionCode;
import koreatech.cse.repository.RegionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

// clean package -p prod

@RestController
@RequestMapping("/region")
public class RegionController {
    @Inject
    private RegionMapper regionMapper;

    @Transactional
    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<RegionCode> regionRegistry(@RequestBody RegionCode regionCode) {
        regionMapper.insert(regionCode);
        System.out.println("RegionCode = " + regionCode);

        return new ResponseEntity<RegionCode>(regionCode, HttpStatus.OK);
    }
}