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

package org.jledit.command.undo;


import org.jledit.command.Command;
import org.jledit.Editor;

public class RedoCommand implements UndoContextAware, Command {

    private UndoContext context;

    public RedoCommand() {
        this.context = new UndoContext();
    }

    public RedoCommand(Editor editor, UndoContext context) {
        this.context = context;
    }

    public void setUndoContext(UndoContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        UndoableCommand undoableCommand = context.redoPop();
        undoableCommand.execute();
        context.undoPush(undoableCommand);
    }
}