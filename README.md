# Formative Assessment

## Learning Outcomes

| Learning Outcome | Section |
|---|---|
| Build Pipelines / Scripting | Section 1 |
| OOP | Section 2 |
| Relational Database Design | Section 3 |

---

## Duration

**Total time: 1 hour 45 minutes**

| Section | Recommended Time |
|---|---|
| Section 1 — Build and Release Management | 35 minutes |
| Section 2 — Practical Implementation | 40 minutes |
| Section 3 — Database Schema Design | 30 minutes |

---

## Scoring

| Section | Marks |
|---|---|
| Section 1 — Build and Release Management | 21 |
| Section 2 — Practical Implementation | 20 |
| Section 3 — Database Schema Design | 20 |
| **Total** | **61** |

---

# Section 1 — Build and Release Management

## Scenario

You have joined a small team whose Java application has always been released by
whichever developer happens to be free, building it from their own IDE and copying the
jar across by hand. The team has just agreed to move to Maven and a shared pipeline,
and you have been asked to explain the reasoning to the rest of the team before the
change lands.

Write your answers in `answers.txt`, under the matching question heading. Do not
change the format of the file or create a new one.

| Task | Marks | What earns marks |
|---|---|---|
| Q1.1 — Works on my machine | 5 | Identifies three distinct problems caused by an unreproducible, single-developer build (3), explains what a shared automated build gives the team instead (2) |
| Q1.2 — Packaging and deployment | 5 | Correctly distinguishes packaging from deployment (2), places each correctly in the release process (1), explains why compiled `.class` files alone are not something a user can run (2) |
| Q1.3 — Dependencies and the pom | 5 | Accurate description of what `pom.xml` is for (2), correct definition of a dependency (1), explains why dependencies are declared in the pom rather than each developer sourcing the jars themselves (2) |
| Q1.4 — Versioning a release | 6 | Correct version number and sound reason for **(a)** (2), for **(b)** (2), and for **(c)** (2) |

---

### Q1.1 — Works On My Machine *(5 marks)*

A developer copies their locally built jar onto the shared server, and it fails to
start. Their own machine has run it fine all week.

Explain what a manual, single-developer build process makes possible that it
shouldn't, and what a repeatable automated build gives the team instead.

> Identify at least three distinct problems.

---

### Q1.2 — Packaging and Deployment *(5 marks)*

Once `mvn compile` succeeds, the team still can't hand anything to a user.

Explain what it means to **package** an application and what it means to **deploy**
it, where each one sits in the release process, and why the compiled `.class` files on
their own are not enough to give to a user.

---

### Q1.3 — Dependencies and the pom *(5 marks)*

The application depends on a JDBC driver the team did not write. That driver's jar is
not committed to the repository, yet every developer's build ends up with a copy of
it.

Explain what `pom.xml` is for and what a dependency is in this context. Then explain
why dependencies are declared in the pom rather than each developer downloading the
jars themselves.

---

### Q1.4 — Versioning a Release *(6 marks)*

The application is currently released at version `3.4.0`.

Each of the following changes is made **independently**, starting from `3.4.0` in
every case. For each one, state the new version number and explain why:

- **(a)** You fix a defect that was causing incorrect fees to be charged.
- **(b)** You add a new, optional configuration setting with a sensible default. No
  existing behaviour changes.
- **(c)** You remove a public method that other teams still call.

---

# Section 2 — Practical Implementation

## Scenario

You have taken over the starter project in this repository, a small courier delivery
service. Different delivery methods charge different fees and take different numbers
of days to arrive. `ExpressDelivery` and `StandardDelivery` are both fully
implemented.

The build is currently broken, and the project does not compile.

| Task | Marks | What earns marks |
|---|---|---|
| Q2.1 — Fix the build | 10 | Correctly identifies and adds the missing dependency using its Maven Central coordinates (5), dependency declared with the correct scope (2), `mvn package` succeeds and produces a jar that runs with `java -jar` (3) |
| Q2.2 — Fix the OOP structure | 4 | Correctly identifies why `DeliveryMethod` does not compile (2), restores it as an abstract class that subclasses can call `super(...)` on, without changing the two method declarations or any of the fee logic (2) |
| Q2.3 — Build a pipeline | 6 | Correct `stages` defined in the correct order (2), `compile` job correctly defined in its own stage and runs the matching Makefile target (2), `package` job correctly defined in its own stage, runs the matching Makefile target and keeps the jar as an artifact (2) |

---

### Q2.1 — Fix the Build *(10 marks)*

A certain missing dependency is preventing this project from building correctly. Find
the correct dependency and add it to `pom.xml` so that `mvn package` succeeds and
produces a runnable jar.

> You can search for artefacts on [Maven Central](https://mvnrepository.com/repos/central).

---

### Q2.2 — Fix the OOP Structure *(4 marks)*

`DeliveryMethod` is meant to be an abstraction that `ExpressDelivery` and
`StandardDelivery` build on. It holds the state every delivery shares and calls out to
each subclass's own fee logic, but as it stands the project does not compile.

Analyse `DeliveryMethod` and apply the fix so that both subclasses compile
correctly. Leave the two abstract method declarations as they are, and do not change
any of the fee or transit-day logic in either subclass.

> You may not modify the tests.
>
> This should take no more than 5 minutes.

---

### Q2.3 — Build a Pipeline *(6 marks)*

This project builds using GitLab CI. The pipeline is defined in `.gitlab-ci.yml` and
runs against the same `Makefile` targets you would use locally: `make compile`,
`make test`, and `make package`.

The image and the `test` job are provided as a worked example. Define the `stages`
for this pipeline, and add a job for each remaining stage, so that once pushed, the
pipeline compiles the code, runs the test suite, and then packages the application,
keeping the built jar as a pipeline artifact.
> You are allowed to use the following [CI/CD docs](https://docs.gitlab.com/ci/yaml/).

---

# Section 3 — Database Schema Design

## Scenario

The courier service currently only prints to the console. The next step is to persist
`clients` and `shipments` in a real database. `resources/erd.png` is the entity
relationship diagram for the two tables you need to create:

![ERD](../../oop-044-bsdb/resources/erd.png)

A `Database` class is already provided and knows how to open a JDBC connection to a
SQLite database. `DatabaseSchema.createSchema(Connection connection)` is where the
schema itself is created, but the method body is currently empty.

> `resources/SQL-Cheat-Sheet.pdf` is provided as a reference if you need a refresher
> on SQL syntax (data types, constraints, `CREATE TABLE`, etc.).

| Task | Marks | What earns marks |
|---|---|---|
| Q3.1 — Create the `clients` table | 8 | Correct columns and types (4), `id` declared as the primary key and `full_name`/`phone` declared `NOT NULL` (4) |
| Q3.2 — Create the `shipments` table | 8 | Correct columns and types (4), `id` declared as the primary key and `weight_kg`/`delivery_method`/`client_id` declared `NOT NULL` (4) |
| Q3.3 — Foreign key | 4 | `shipments.client_id` correctly declared as a foreign key referencing `clients.id` (4) |

---

### Q3.1 – Q3.3 — Create the Schema *(20 marks)*

Implement `DatabaseSchema.createSchema(Connection connection)` so that it creates the
`clients` and `shipments` tables exactly as shown in the ERD, using standard SQL
`CREATE TABLE` statements executed through the JDBC `Connection` you are given.

> This task is schema creation only — you are not inserting or querying any data.
>
> Run `mvn test` to check your schema against `DatabaseSchemaTest`.

---

### End of Assessment

---

## Project structure

```
courier-assessment/
  README.md
  answers.txt
  pom.xml
  Makefile
  .gitlab-ci.yml
  resources/
    erd.png
    SQL-Cheat-Sheet.pdf
  src/
    main/java/za/co/wethinkcode/courier/
      DeliveryMethod.java
      ExpressDelivery.java
      StandardDelivery.java
      Main.java
      Database.java
      DatabaseSchema.java
    test/java/za/co/wethinkcode/courier/
      ExpressDeliveryTest.java
      StandardDeliveryTest.java
      DatabaseSchemaTest.java
```

## Useful commands

```bash
# Compile the source code
mvn compile

# Check that all dependencies are satisfied and up to date
mvn verify

# Run the test suite
mvn test

# Package the application into a jar
mvn package

# Run the packaged jar
java -jar target/courier-assessment-jar-with-dependencies.jar
```

The `Makefile` wraps the same commands, and is what the GitLab CI pipeline runs:

```bash
make compile
make test
make package
```
