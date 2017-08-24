package cn.xinbee.rcs.core.io;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FieldDescriptor {
    
    private final String field;
    private final String caption;

    public String getField() {
        return field;
    }

    public String getCaption() {
        return caption;
    }
    
    public FieldDescriptor(String field, String caption) {
        this.field = field;
        this.caption = caption;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FieldDescriptor) {
            final FieldDescriptor other = (FieldDescriptor) o;
            return new EqualsBuilder()
                    .append(caption, other.caption)
                    .append(field, other.field)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(caption)
                .append(field)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("caption", caption)
                .append("field", field)
                .toString();
    }
}
