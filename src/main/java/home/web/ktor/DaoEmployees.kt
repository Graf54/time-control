package home.web.ktor

object DaoEmployees {
    val list = ArrayList<Employee>()
    fun getAllEmployees(): List<Employee> {
        if (list.isEmpty()) {
            list.add(Employee(1, "John", "hoho@mail.ru", "Novosibirsk"))
            list.add(Employee(2, "Smart", "hoho@mail.ru", "Novosibirsk"))
            list.add(Employee(3, "Not", "hoho@mail.ru", "Novosibirsk"))
            list.add(Employee(4, "Bak", "hoho@mail.ru", "Novosibirsk"))
        }
        return list
    }

    fun getId(): Int {
        var id = 1
        list.maxBy { it.id }?.let { id = it.id }
        return id
    }

    fun getEmployee(idEmpl: Int): Employee {
        return list.first { it.id == idEmpl }
    }

    fun createEmployee(name: String, email: String, city: String) {
        list.add(Employee(getId(), name, email, city))
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
                    var email: String, var city: String)