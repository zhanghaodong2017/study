package com.zhd.ultimate.sociology.controller;

import com.zhd.ultimate.sociology.entity.${firstUpTableName};
import com.zhd.ultimate.sociology.service.${firstUpTableName}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping("/${tableEntityName}")
public class ${firstUpTableName}Controller {

    @Autowired
    private ${firstUpTableName}Service ${tableEntityName}Service;


    @InitBinder("${tableEntityName}")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("${tableEntityName}.");
    }

    @RequestMapping("/queryAll${firstUpTableName}")
    public String queryAll${firstUpTableName}(Model model) {
        List <${firstUpTableName}> ${tableEntityName}List = ${tableEntityName}Service.queryAll${firstUpTableName}();
        model.addAttribute("${tableEntityName}List", ${tableEntityName}List);
        return "${tableEntityName}/${tableEntityName}-query";
    }

    @RequestMapping("/showAdd")
        public String showAdd() {
        return "${tableEntityName}/${tableEntityName}-add";
    }

    @RequestMapping("/showUpdate")
    public String showUpdate(@RequestParam("guid") String guid, Model model) {
        ${firstUpTableName} ${tableEntityName} = ${tableEntityName}Service.query${firstUpTableName}(guid);
        model.addAttribute("${tableEntityName}", ${tableEntityName});
        return "${tableEntityName}/${tableEntityName}-update";
    }

    @RequestMapping("/add")
    public String add(${firstUpTableName} ${tableEntityName}, Model model) {
        ${tableEntityName}Service.add(${tableEntityName});
        return queryAll${firstUpTableName}(model);
    }

    @RequestMapping("/update")
    public String update(${firstUpTableName} ${tableEntityName}, Model model) {
        ${tableEntityName}Service.update(${tableEntityName});
        return queryAll${firstUpTableName}(model);
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("guid") String guid, Model model) {
        ${tableEntityName}Service.delete(guid);
        return queryAll${firstUpTableName}(model);
    }

}
