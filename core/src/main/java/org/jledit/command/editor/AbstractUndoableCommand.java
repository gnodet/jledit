/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jledit.command.editor;

import org.jledit.command.undo.UndoableCommand;
import org.jledit.ConsoleEditor;

/**
 *
 */
public abstract class AbstractUndoableCommand implements UndoableCommand {

    private final ConsoleEditor editor;
    private int line;
    private int column;

    public AbstractUndoableCommand(ConsoleEditor editor) {
        this.editor = editor;
    }

    /**
     * Moves cursor to the right place for the undo operation.
     */
    @Override
    public void undo() {
        editor.move(line, column);
    }

    /**
     * Stores cursor coordinates.
     */
    @Override
    public void execute() {
        line = editor.getLine();
        column = editor.getColumn();
    }

    public ConsoleEditor getEditor() {
        return editor;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}