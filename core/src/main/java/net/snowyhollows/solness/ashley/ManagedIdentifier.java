package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.Component;

public class ManagedIdentifier implements Component {
    public final int identifier;

    public ManagedIdentifier(int identifier) {
        this.identifier = identifier;
    }
}
