package ch.bildspur.pointcloud.visual;

import ch.bildspur.pointcloud.attribute.IntAttribute;
import ch.bildspur.pointcloud.attribute.PointCloudAttribute;

import java.util.HashMap;
import java.util.Map;

public class PointCloudBuffer {
    private int length;
    private HashMap<String, PointCloudAttribute> attributes;
    private IntAttribute indicesAttribute;
    private boolean dirty = false;

    public PointCloudBuffer(int length) {
        this.length = length;
        this.attributes = new HashMap<>();
        this.indicesAttribute = new IntAttribute("index", 1);
    }

    public void allocate() {
        for(PointCloudAttribute attribute : attributes.values()) {
            attribute.allocate(length);
        }

        // allocate and fill indices attribute
        indicesAttribute.allocate(length);
        for(int i = 0; i < length; i++) {
            this.indicesAttribute.set(i, i);
        }
    }

    public void addAttribute(PointCloudAttribute attribute) {
        this.attributes.put(attribute.getName(), attribute);
    }

    public <T extends PointCloudAttribute> T getAttribute(String name) {
        return (T)attributes.get(name);
    }

    public Map<String, PointCloudAttribute> getAttributes() {
        return attributes;
    }

    public IntAttribute getIndicesAttribute() {
        return indicesAttribute;
    }

    public int getLength() {
        return length;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
}