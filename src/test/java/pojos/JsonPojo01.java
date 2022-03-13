package pojos;

public class JsonPojo01 {
    //pojo ile artik expected datalari biz burda olusturacaz testDtalarda degil
   /**
 {
 "userId": 1,
 "id": 3,
 "title": "fugiat veniam minus",
 "completed": false
 }

   */

    private int userId;
    private int id;
    private String title;
    private boolean completed;




    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public JsonPojo01(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public JsonPojo01() {
    }


    @Override
    public String toString() {
        return "JsonPojo01{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
