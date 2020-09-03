<template>
    <div class="diagram_wrap">
        <img :src="require(`@/assets/images/diagram.png`)" class="diagramImg" />
    </div>
</template>

<script>
export default {
    name: "Diagram"
}
</script>

<style>
.diagram_wrap {
    margin:0 auto;
    width: 70%;
}

.diagramImg {
    margin-top:50px;
    margin-bottom: 50px;
    width: 900px;
}

@media ( min-width: 1300px ) {
    .diagramImg {
        width: 1200px;
    }
}

@media (min-width: 769px) and (max-width: 480px) {
    .diagramImg {
        width: 900px;
    }
}

@media screen and (max-width: 768px) {
    .diagramImg {
        width: 300px;
    }
}

</style>