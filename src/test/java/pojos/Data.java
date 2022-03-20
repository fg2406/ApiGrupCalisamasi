package pojos;

public class Data {
    /** GetRequestWithPojo01:
     http://dummy.restapiexample.com/api/v1/employee/1 url ‘ine bir get request
     gönderildiğinde , dönen response ‘un,
     Status kodunun 200 ve response body’nin
     {
     "status": "success",
     "data": {
     "id": 1,
     "employee_name": "Tiger Nixon",
     "employee_salary": 320800,
     "employee_age": 61,
     "profile_image": ""
     },
     "message": "Successfully! Record has been fetched."
     }
     Olduğunu test edin*/

    private int id;
    private String employee_name;
    private Integer employee_salary;
    private Integer employee_age;
    private String profile_image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Integer getEmployee_salary() {
        return employee_salary;
    }

    public void setEmployee_salary(Integer employee_salary) {
        this.employee_salary = employee_salary;
    }

    public Integer getEmployee_age() {
        return employee_age;
    }

    public void setEmployee_age(Integer employee_age) {
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public Data() {
    }

    public Data(int id, String employee_name, Integer employee_salary, Integer employee_age, String profile_image) {
        this.id = id;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
        this.profile_image = profile_image;
    }


    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", employee_name='" + employee_name + '\'' +
                ", employee_salary=" + employee_salary +
                ", employee_age=" + employee_age +
                ", profile_image='" + profile_image + '\'' +
                '}';
    }
}
