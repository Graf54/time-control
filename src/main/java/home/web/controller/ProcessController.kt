package home.web.controller

import home.db.dao.Processes
import home.web.entity.ProcessEntity
import home.web.entity.ServiceProcessEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class ProcessController {

    /*It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/
    @RequestMapping(value = ["/add"], method = [RequestMethod.POST])
    fun save(@ModelAttribute("proc") entity: ProcessEntity): String {
        if (!Processes.nameExist(entity.name)) {
            ServiceProcessEntity.add(entity)
        }
        return "redirect:/processes"//will redirect to processes request mapping
    }

    /* It provides list of employees in model object */
    @RequestMapping("/processes")
    fun processes(m: Model): String {
        val list = ServiceProcessEntity.getList()
        m.addAttribute("list", list)
        return "processes"
    }


    @RequestMapping("/")
    fun index(): String {
        return "redirect:/processes"
    }

    @RequestMapping("/*")
    fun allNotInclude(): String {
        return "redirect:/processes"
    }


    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = ["/edit/{id}"])
    fun edit(@PathVariable id: Int, m: Model): String {
        val list = ServiceProcessEntity.list
        list.forEach { x -> x.editCode = 0 }
        ServiceProcessEntity.getProcById(id).editCode = 1
        m.addAttribute("list", list)
        return "redirect:/processes"
    }

    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = ["/update/{id}"], method = [RequestMethod.POST])
    fun update(@PathVariable id: Int, @ModelAttribute("proc") entity: ProcessEntity): String {
        if (Processes.anotherNameExist(entity.name, id)) {
            val processEntity = ServiceProcessEntity.getProcById(id)
            processEntity.dbHasName = 1
        } else {
            val processFromList = ServiceProcessEntity.getProcById(id)
            processFromList.name = entity.name
            processFromList.timeLimit = entity.timeLimit
            processFromList.editCode = 0
            processFromList.dbHasName = 0
            ServiceProcessEntity.update(processFromList)
        }

        return "redirect:/processes"
    }

    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value = ["/cancel"])
    fun cancel(m: Model): String {
        val list = ServiceProcessEntity.list
        list.forEach { x ->
            x.editCode = 0
            x.dbHasName = 0
        }
        m.addAttribute("list", list)
        return "redirect:/processes"
    }

    /* It deletes record for the given id in URL and redirects to /processes */
    @RequestMapping(value = ["/delete/{id}"], method = [RequestMethod.GET])
    fun delete(@PathVariable id: Int): String {
        ServiceProcessEntity.delete(id)
        return "redirect:/processes"
    }


}