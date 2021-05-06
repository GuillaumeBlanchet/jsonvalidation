# json-validation sample project

This project comes along with my 
[blog post about three ways to do json validation](https://guillaumeblanchet.medium.com/three-ways-to-validate-json-in-java-4b38d95ba7c).

It shows how to use either 
- serialization (with [Jackson lib](https://github.com/FasterXML/jackson));  
- json-schema (with [networknt](https://github.com/networknt/json-schema-validator) or [everit lib](https://github.com/everit-org/json-schema)) or;
- Jpath (with [Jayway lib](https://github.com/json-path/JsonPath))

to perform json validations.

# Tips when writing a validator

In order to implement a clean validator unit tests framework, see how the 
`validateAnInvalidity` unit test is written and read my article above.