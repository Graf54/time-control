package home.web.ktor

object DaoEmployees {
    val list = ArrayList<Employee>()
    fun getAllEmployees(): List<Employee> {
        if (list.isEmpty()) {
            createEmployee("John", "hoho@mail.ru", "Novosibirsk")
            createEmployee("Smart", "hoho@mail.ru", "Novosibirsk")
            createEmployee("Not", "hoho@mail.ru", "Novosibirsk")
            createEmployee("Bak", "hoho@mail.ru", "Novosibirsk")
        }
        return list
    }

    fun getId(): Int {
        var id = 0
        list.maxBy { it.id }?.let { id = it.id }
        return ++id
    }

    fun getEmployee(idEmpl: Int): Employee {
        return list.first { it.id == idEmpl }
    }

    fun createEmployee(name: String, email: String, city: String) {
        list.add(Employee(getId(), name, email, city, 0))
    }

    fun updateEmployee(id: Int, name: String, email: String, city: String) {
        val employee = getEmployee(id)
        employee.name = name
        employee.email = email
        employee.city = city
    }

    fun deleteEmployee(id: Int) {
        list.remove(getEmployee(id))
    }

}

data class Employee(val id: Int, var name: String,
                    var email: String, var city: String, var edit: Int = 0)