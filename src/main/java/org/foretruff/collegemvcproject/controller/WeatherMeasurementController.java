package org.foretruff.collegemvcproject.controller;

import lombok.RequiredArgsConstructor;
import org.foretruff.collegemvcproject.dto.MeasurementCreateEditDto;
import org.foretruff.collegemvcproject.dto.MeasurementFilter;
import org.foretruff.collegemvcproject.dto.PageResponse;
import org.foretruff.collegemvcproject.service.MeasurementService;
import org.foretruff.collegemvcproject.util.ImageUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/measurements")
public class WeatherMeasurementController {

    private final MeasurementService measurementService;
    private final ImageUtil imageUtil;

    @GetMapping
    public String findAll(Model model, MeasurementFilter filter, Pageable pageable) {
        var page = measurementService.findAll(filter, pageable);
        model.addAttribute("measurements", PageResponse.of(page));
        model.addAttribute("filter", filter);
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        return "measurements";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        return measurementService.findById(id)
                .map(measurement -> {
                    model.addAttribute("measurement", measurement);
                    if (measurement.getPhoto() != null && measurement.getPhoto().length > 0) {
                        model.addAttribute("imageUrl", imageUtil.getImageUrl(measurement.getPhoto()));
                    }
                    return "measurement";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/add")
    public String registration(Model model, @ModelAttribute("measurement") MeasurementCreateEditDto measurement) {
        model.addAttribute("measurement", measurement);
        return "add_measurement";
    }

    @PostMapping
    public String create(@ModelAttribute @Validated MeasurementCreateEditDto measurement,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("measurement", measurement);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/measurements/add";
        }

        return "redirect:/measurements/" + measurementService.create(measurement).getId();
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @Validated MeasurementCreateEditDto measurement,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("measurement", measurement);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/measurements/" + id;
        }
        return measurementService.update(id, measurement)
                .map(it -> "redirect:/measurements/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (!measurementService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/measurements";
    }

}
