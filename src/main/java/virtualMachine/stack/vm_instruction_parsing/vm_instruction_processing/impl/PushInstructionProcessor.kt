package virtualMachine.stack.vm_instruction_parsing.vm_instruction_processing.impl

import virtualMachine.stack.memory.GlobalVirtualMemory
import virtualMachine.stack.datawrappers.Word
import virtualMachine.stack.datawrappers.instruction.Instruction
import virtualMachine.stack.memory.MemorySegments
import virtualMachine.stack.vm_instruction_parsing.vm_instruction_processing.InstructionProcessor

class PushInstructionProcessor : InstructionProcessor {

    override fun processInstruction(instruction: Instruction, virtualMemory: GlobalVirtualMemory) {

        val index = instruction.address
        val segment = MemorySegments.getFromName(instruction.operand)

        val storedVariable : Word = virtualMemory.getFromMemory(index, segment)
        virtualMemory.pushToStack(storedVariable)
    }

}