package client.gui;

import java.util.HashSet;
import java.util.Set;

class PositionSet extends HashSet<Position> {

    PositionSet() {
        super();
    }

    void filter(String status) {
        Set<Position> toRemove = new HashSet<>();
        for (Position position : this) {
            if (!position.getStatus().equals(status)) {
                toRemove.add(position);
            }
        }
        this.removeAll(toRemove);
    }
}
