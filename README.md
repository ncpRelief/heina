# heina
a springboot web application about HEINA

## Backend

Spring boot

Run: `mvn spring-boot:run`

**APIs:**

```java
    @GetMapping("/listUsers")
    public JSONObject getUserList() {
        JSONArray users = userService.listUser();
        JSONObject result = new JSONObject();
        result.put("msg", "查询成功");
        result.put("status", 0);
        result.put("users", users);
        return result;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
```

```java
@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello(){
        return  "hello world";
    }
```

## Frontend

React

Run: `npm start`