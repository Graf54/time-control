package home.db

/*val path = File(".").path
Database.connect("jdbc:h2:./history.db;AUTO_SERVER=TRUE", driver = "org.h2.Driver")

transaction {
SchemaUtils.drop(MyProcess, ProcessDays)
SchemaUtils.create(MyProcess, ProcessDays)

val saintPetersburgId = Cities.insert {
it[name] = "St. Petersburg"
} get Cities.id

val munichId = Cities.insert {
it[name] = "Munich"
} get Cities.id

Cities.insert {
it[name] = "Prague"
}

Users.insert {
it[id] = "andrey"
it[name] = "Andrey"
it[cityId] = saintPetersburgId
}

Users.insert {
it[id] = "sergey"
it[name] = "Sergey"
it[cityId] = munichId
}

Users.insert {
it[id] = "eugene"
it[name] = "Eugene"
it[cityId] = munichId
}

Users.insert {
it[id] = "alex"
it[name] = "Alex"
it[cityId] = null
}

Users.insert {
it[id] = "smth"
it[name] = "Something"
it[cityId] = null
}

Users.update({ Users.id eq "alex" }) {
it[name] = "Alexey"
}

Users.deleteWhere { Users.name like "%thing" }

println("All cities:")

for (city in Cities.selectAll()) {
println("${city[Cities.id]}: ${city[Cities.name]}")
}

println("Manual join:")
(Users innerJoin Cities).slice(Users.name, Cities.name).select {
(Users.id.eq("andrey") or Users.name.eq("Sergey")) and
       Users.id.eq("sergey") and Users.cityId.eq(Cities.id)
}.forEach {
println("${it[Users.name]} lives in ${it[Cities.name]}")
}

println("Join with foreign key:")


(Users innerJoin Cities).slice(Users.name, Users.cityId, Cities.name)
.select { Cities.name.eq("St. Petersburg") or Users.cityId.isNull() }.forEach {
   if (it[Users.cityId] != null) {
       println("${it[Users.name]} lives in ${it[Cities.name]}")
   } else {
       println("${it[Users.name]} lives nowhere")
   }
}

println("Functions and group by:")

((Cities innerJoin Users).slice(Cities.name, Users.id.count()).selectAll().groupBy(Cities.name)).forEach {
val cityName = it[Cities.name]
val userCount = it[Users.id.count()]

if (userCount > 0) {
   println("$userCount user(s) live(s) in $cityName")
} else {
   println("Nobody lives in $cityName")
}
}

//        SchemaUtils.drop (Users, Cities)

}*/