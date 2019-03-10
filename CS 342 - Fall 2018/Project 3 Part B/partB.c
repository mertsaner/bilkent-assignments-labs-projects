#include <linux/module.h> /* Needed by all modules */
#include <linux/kernel.h> /* Needed for KERN_INFO */
#include <linux/moduleparam.h> // Needed for passing parameters
#include <linux/init.h>
#include <linux/sched.h> // Needed for struct task_struct
#include <linux/sched/signal.h> // Needed for for_each_process
#include <asm/page.h> // Needed for page table
#include <asm/pgtable.h> // Needed for page table
#include <linux/highmem.h> // Needed for page table
MODULE_LICENSE("GPL");

int processid;
char *virtaddr = "000000000000";
module_param(processid, int, 0);
//module_param(virtaddr, charp, 0);
unsigned long virtaddress;

struct task_struct * theprocess;
struct mm_struct *pmm;

void find_pcb(void){
	struct task_struct* task;
	pgd_t *pgd;
	p4d_t *p4d;
	pud_t *pud;
	pmd_t *pmd;
	pte_t *pte;
	
	for_each_process(task) {
		//printk(KERN_INFO "current pid=%d", task->pid);	
		pmm = task->active_mm;
		if(task->pid == processid){
			printk(KERN_INFO "found the process with pid=%d\n", task->pid);
			struct vm_area_struct *vma = 0;
			unsigned long vpage;
			if (task->mm && task->mm->mmap)
				for (vma = task->mm->mmap; vma; vma = vma->vm_next)
					for (vpage = vma->vm_start; vpage < vma->vm_end; vpage += PAGE_SIZE){
						//unsigned long phys = virt2phys(task->mm, vpage);
						struct page* page;
						struct page* phys;
						pgd_t *pgd = pgd_offset(pmm, vpage);
						if (!pgd_none(*pgd) && !pgd_bad(*pgd)){
							//printk(KERN_INFO "pgd");
							p4d = p4d_offset(pgd, vpage);
							if (!p4d_none(*p4d) && !p4d_bad(*p4d)){
								//printk(KERN_INFO "p4d");
								pud = pud_offset(p4d, vpage);
								if (!pud_none(*pud) && !pud_bad(*pud)){
									//printk(KERN_INFO "pud");
									pmd = pmd_offset(pud, vpage);
									if (!pmd_none(*pmd) && !pmd_bad(*pmd)){
										//printk(KERN_INFO "pmd");
										if ((pte = pte_offset_map(pmd, vpage))){
											//printk(KERN_INFO "pte");
											if (page = pte_page(*pte)){
												if(phys != page_to_phys(page)){
													phys = page_to_phys(page);
													//printk(KERN_INFO "Input Virtual: %lx\n", virtaddr);
													printk(KERN_INFO "Virtual: %lx / Physical: %lx\n", vpage, phys);
													if(virtaddr == vpage)
														return;
												}
												pte_unmap(pte);
											}
										}
									}
								}
							}
						}
					}

			printk(KERN_INFO "Code: start=%lu, end=%lu, size=%lu\n", pmm->start_code, pmm->end_code, pmm->end_code - pmm->start_code);
			printk(KERN_INFO "Data: start=%lu, end=%lu, size=%lu\n", pmm->start_data, pmm->end_data, pmm->end_data - pmm->start_data);
			printk(KERN_INFO "Heap: start=%lu, end=%lu, size=%lu\n", pmm->start_brk, pmm->brk, pmm->brk - pmm->start_brk);
			printk(KERN_INFO "Stack: start=%lu, end=%lu, size=%lu\n", pmm->start_stack, pmm->start_stack - pmm->stack_vm, pmm->stack_vm);
			printk(KERN_INFO "Main args: start=%lu, end=%lu, size=%lu\n", pmm->arg_start, pmm->arg_end,pmm->arg_end - pmm->arg_start);
			printk(KERN_INFO "Env. vars: start=%lu, end=%lu, size=%lu\n", pmm->env_start, pmm->env_end, pmm->env_end - pmm->env_start);
			printk(KERN_INFO "# of frames: %lu\n", pmm->hiwater_rss);
			printk(KERN_INFO "Total VM used: %lu\n", pmm->total_vm);
			break;
		}
		
	}
}
int init_module(void){
	printk(KERN_INFO "Hello, World!\n");
	printk(KERN_INFO "Input pid=%d\n", processid);
	//kstrtol(virtaddr, 16, &virtaddress);
	find_pcb();
	return 0;
}
void cleanup_module(void){
	printk(KERN_INFO "Goodbye, World.\n");
}
