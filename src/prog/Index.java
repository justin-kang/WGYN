import java.io.Serializable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;

public class Index implements Serializable {

    private static final long serialVersionUID = 1L;
    // Stores a list of input numbers and set of possible results
    private Map<List<Integer>, Set<Integer>> index;

    public Index() {
        index = new HashMap<>();
    }

    // Gets the results given a list of numbers
    public Set<Integer> get(List<Integer> list) {
        if (list == null || list.isEmpty()) return new HashSet<>();
        Collections.sort(list);
        return index.containsKey(list) ? index.get(list) : new HashSet<>();
    }

    // Adds a result for a list of numbers
    public void add(List<Integer> list, int result) {
        if (list == null || list.isEmpty()) return;
        Collections.sort(list);
        Set<Integer> set = index.get(list);
        if (set == null) set = new HashSet<>();
        set.add(result);
        index.put(list, set);
    }

    // Load the index from a file
    public static Index load(String file) 
        throws IOException, ClassNotFoundException {
        InputStream fin = null;
        ObjectInputStream oin = null;
        Index result = null;
        try {
            fin = new FileInputStream(file);
            oin = new ObjectInputStream(new GZIPInputStream(fin));
            result = (Index)oin.readObject();
        }
        catch (IOException e) {
        }
        finally {
            try {
                if (oin != null) oin.close();
                else if (fin != null) fin.close();
            }
            catch (IOException e) {
            }
        }
        return result;
    }

    // Save the index to a file
    public void save(String file) throws IOException {
        FileOutputStream fout = null;
        ObjectOutputStream oout = null;
        try {
            fout = new FileOutputStream(file);
            oout = new ObjectOutputStream(new GZIPOutputStream(fout));
            oout.writeObject(this);
        }
        catch (IOException e) {
        }
        finally {
            try {
                if (oout != null) oout.close();
                else if (fout != null) fout.close();
            }
            catch (IOException e) {
            }
        }
    }
}
