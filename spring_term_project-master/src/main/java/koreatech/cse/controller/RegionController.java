package koreatech.cse.controller;

import koreatech.cse.domain.ReSearchable;
import koreatech.cse.domain.RegionCode;
import koreatech.cse.repository.RegionMapper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

// clean package -p prod

@Controller

@RequestMapping("/region")
public class RegionController {
    @Inject
    private RegionMapper regionMapper;

    @Transactional
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String regionRegistry(@ModelAttribute RegionCode regionCode, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors ) {
                System.out.println (error.getObjectName() + " - " + error.getDefaultMessage());
            }
        }

        regionMapper.insert(regionCode);
        System.out.println("RegionCode = " + regionCode);
        return "redirect:/region/list";
    }

    @RequestMapping("/edit")
    public String edit(Model model, @RequestParam String code) {
        RegionCode regionCode = regionMapper.findOne(code);
        model.addAttribute("region", regionCode);
        return "edit";
    }

    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public String edit(@ModelAttribute RegionCode regionCode) {
        regionMapper.update(regionCode);
        return "redirect:/region/list";
    }

    @RequestMapping(value="/delete", method= RequestMethod.GET)
    public String delete(@RequestParam String code) {
        regionMapper.delete(code);
        return "redirect:/region/list";

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(required=false) String state,
                       @RequestParam(required=false) String city,
                       @RequestParam(required=false) String sub1,
                       @RequestParam(required=false) String sub2,
                       @RequestParam(required=false) String order) {
        ReSearchable searchable = new ReSearchable();
        searchable.setState(state);
        searchable.setCity(city);
        searchable.setSub1(sub1);
        searchable.setSub2(sub2);
        searchable.setOrderParam(order);
        //model.addAttribute("regions", regionMapper.findByProvider(searchable));
        model.addAttribute("regions", regionMapper.findByScript(searchable));
        return "list";
    }
}
