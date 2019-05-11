package virtualMachine.stack.vm_instruction_parsing.vm_instruction_processing.impl;

import org.jetbrains.annotations.NotNull;
import virtualMachine.stack.datawrappers.VmFunction;
import virtualMachine.stack.datawrappers.Word;
import virtualMachine.stack.datawrappers.instruction.Instruction;
import virtualMachine.stack.memory.GlobalVirtualMemory;
import virtualMachine.stack.vm_instruction_parsing.vm_instruction_processing.InstructionProcessor;

import static virtualMachine.stack.memory.MemorySegments.ARGUMENT;

/**
 * function f n -> start of code named f with n local variables
 * call f m -> call function f with m stack values as arguments
 * return -> returns the calling function
 */
public class FunctionProcessor implements InstructionProcessor {

    @Override
    public void processInstruction(@NotNull Instruction instruction, @NotNull GlobalVirtualMemory virtualMemory) {
        var command = instruction.getCommand();

        switch (command) {
            case FUNCTION:
                virtualMemory.pushToCallStack(new VmFunction(instruction.getOperand(), instruction.getNumericValue().byteValue()));

                for (int i = 1; i <= instruction.getNumericValue(); i++) {
                    //advnace stack pointer this many times, push 0s to global stack
                }

                break;
            case CALL:
                //a complex command - need to execute code in the correct vm file
                //need to push some things to the global stack
                //think first thing pushed in function address where called from
                int locCalledFrom = virtualMemory.getGlobalStackPointer();
                //load sp, lcl, arg, this, that into global stack

                virtualMemory.callFunction(instruction.getOperand(), instruction.getNumericValue());
                //set sp to the location filled up to into the global stack
                break;
            case RETURN:
                virtualMemory.popCallStack();
                break;
        }
    }

}
