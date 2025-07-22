# Spring Boot API - Docker

This project is a Spring Boot application containerized with Docker, ready for local execution.

---

## **Steps to Build and Run**

### **1. Build the Application (Maven)**

From the project root, compile and package the JAR:
```
mvn clean package
```

---

### **2. Build the Docker Container**

From the project root (where the `Dockerfile` is located), build the Docker image:
```
docker build -t springboot-api:latest .
```

---

### **3. Run in Detached Mode**

To run the container in **detached** mode (background):
```
docker run -d -p 8080:8080 springboot-api:latest
```

---

### **4. Check Container Logs**

To view the logs of the running container:
```
docker logs -f <container_id>
```
> Replace `<container_id>` with the container ID obtained from the `docker ps` command.

---

### **5. Check Running Containers**

To list all running containers:
```
docker ps
```

---

### **6. Stop the Container**

To stop a running container:
```
docker stop <container_id>
```

---

### **7. Access Swagger UI and Health Endpoint**

- **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **API Base:** [http://localhost:8080/products](http://localhost:8080/products)
- **Health Check:** [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

---
