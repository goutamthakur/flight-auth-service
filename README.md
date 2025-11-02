# 🔐 Flight Auth Service
The goal of this service is to handle user authentication and authorization using email and password, along with token management.


⚠️ **Attention:** This service is currently under development and not ready for use.

## 🍃 Domain Driven Design 
- The service follows a clean architecture inspired by the Hexagonal (Ports and Adapters) design pattern.
- Honestly, there wasn’t a strong need to use full-blown DDD here — I just wanted to experiment with it after reading a blog post about DDD.
- I’m not entirely sure if I’ve implemented everything perfectly, so any feedback or suggestions for improvement are very welcome.

## 📁 Folder Structure

- `api` contains the controllers and DTOs. This is the outermost layer where external clients interact with the application.

- `application` contains all the service classes. It holds the core business logic, independent of outer concerns like DTOs or inner concerns like the database or repositories.

- `domain` defines the interfaces (ports) that are implemented by the infrastructure layer.

- `infrastructure` has main repository and database implementation.

- `common`ontains shared components such as common response models and global exception handling.

## 🛠️ Setup Instructions

All configurations are defined in the `application.properties` file. To override any settings, create a new profile file named `application-dev.properties` and add your datasource or any other custom configurations there.