document.addEventListener("DOMContentLoaded", function() {

  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      this.ul = document.createElement("ul");

      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }

  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  document.addEventListener("click", function(e) {
    const target = e.target;

    if (target.classList.contains("dropdown")) return false;
    if (target.tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }
    if (target.tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    init() {
      this.events();
      this.updateForm();
    }

    events() {
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    updateForm() {
      this.$step.innerText = this.currentStep;

      this.slides.forEach(slide => {
        slide.classList.remove("active");
        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      if (this.currentStep === 5) {
        this.displaySummary();
      }
    }

    // displaySummary() {
    //   const quantity = document.querySelector("#quantity").value;
    //   const institutionRadio = document.querySelector("input[name='institution']:checked");
    //   const institution = institutionRadio ? institutionRadio.parentElement.textContent.trim() : 'Nie wybrano';
    //   const street = document.querySelector("#street").value;
    //   const city = document.querySelector("#city").value;
    //   const zipCode = document.querySelector("#zipCode").value;
    //   const pickUpDateAndTime = document.querySelector("#pickUpDateAndTime").value;
    //   const pickUpComment = document.querySelector("#pickUpComment").value;
    //
    //   const summaryContainer = document.querySelector(".summary");
    //
    //   if (summaryContainer) {
    //     summaryContainer.innerHTML = `
    //                 <div>Ilość worków: ${quantity}</div>
    //                 <div>Instytucja: ${institution}</div>
    //                 <div>Ulica: ${street}</div>
    //                 <div>Miasto: ${city}</div>
    //                 <div>Kod pocztowy: ${zipCode}</div>
    //                 <div>Data i czas odbioru: ${pickUpDateAndTime}</div>
    //                 <div>Uwagi dla kuriera: ${pickUpComment}</div>
    //             `;
    //   } else {
    //     console.error("Nie znaleziono kontenera podsumowania.");
    //   }
    // }
  }

  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
