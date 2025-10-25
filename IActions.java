public interface IActions<T extends Person> {
    public void find(int keys, String keyword);
    public void removeById(String id);
    public void add(T obj);
    public void edit(T a);
}
