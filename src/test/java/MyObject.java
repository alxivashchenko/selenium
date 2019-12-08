public class MyObject {
private  static MyObject myObject;


    public int x;
    public int y;
    private MyObject() {

    }

    @Override
    public boolean equals(Object obj) {
        return (x==((MyObject)obj).x && y ==((MyObject)obj).y);
    }



    public static MyObject getMyObject() {
        if (myObject==null) {
            myObject = new MyObject();
        }
        return  myObject;
    }
}
