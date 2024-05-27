import sys
import torch
from transformers import AutoModelForCausalLM, AutoTokenizer, BitsAndBytesConfig
from safetensors.torch import load_file
import os

os.environ["HF_HUB_DISABLE_SYMLINKS_WARNING"] = "1"

device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

device_map = {
    'transformer.wte': 'cpu',
    'transformer.wpe': 'cpu',
    'transformer.h.0': 'cpu',
    'transformer.h.1': 'cpu',
    # Continue for more layers if needed
}

model_id = "NousResearch/Meta-Llama-3-8B-Instruct"

tokenizer = AutoTokenizer.from_pretrained(model_id)

quantization_config = BitsAndBytesConfig(
    load_in_8bit_fp32_cpu_offload=True,
    device_map=device_map,
)

model = AutoModelForCausalLM.from_pretrained(
    model_id,
    quantization_config=quantization_config,
)

###학습 값 저장 위치 수정
adapter_weights = load_file("C:\\Users\\findu\\Desktop\\finalpjt\\ai_model\\adapter_model.safetensors")
###

model.load_state_dict(adapter_weights, strict=False)

def question(text, prompt):
    chat = [
        {"role": "system", "content": f"{prompt}"},
        {"role": "user", "content": f"{text}"},
        {"role": "assistant", "content": ""}
    ]
    prompt = tokenizer.apply_chat_template(chat, tokenize=False, add_generation_prompt=True)
    token_ids = tokenizer.encode(prompt, add_special_tokens=False, return_tensors="pt").to(device)

    with torch.no_grad():
        output_ids = model.generate(
            token_ids,
            do_sample=True,
            temperature=0.2,
            top_p=0.9,
            max_new_tokens=256,
            num_return_sequences=1,
            eos_token_id=tokenizer.eos_token_id,
        )

    output = tokenizer.decode(output_ids[0][token_ids.size(1):], skip_special_tokens=True)
    return output.strip()

if __name__ == "__main__":
    try:
        if len(sys.argv) != 3:
            print("Usage: python model.py <question> <position>", file=sys.stderr)
            sys.exit(1)

        question_text = sys.argv[1]
        position_text = sys.argv[2]
        prompt_text = "You are an Interviewee. answer the question with only Korean. Please answer only question."

        result = question(question_text, prompt_text)

        ans = list(result.split("assistant"))[0:3]

        for i in range(1, 3):
            ans[i] = result[i].lstrip()

        answer = " ".join(ans)
        
        print(answer)
    except Exception as e:
        print(f"Error: {e}", file=sys.stderr)
        sys.exit(2)