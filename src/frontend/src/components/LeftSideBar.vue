<template>
    <ul class="left-side-bar bg-gradient-primary sidebar" >
        <a class="sidebar-brand d-flex align-items-center justify-content-center" v-bind:href="main">
            <div class="rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3">{{title}}</div>
        </a>
        <hr class="sidebar-divider my-0">
        <li class="nav-item active" v-for="item in list">
            <a class="nav-link" v-bind:href="item.url">
                <i class="fas fa-fw fa-table"></i>
                <span>{{item.name}}</span>
            </a>
        </li>
    </ul>
</template>

<script>
export default {
    name: 'LeftSideBar',
    props: {
        title: String,
        main: String,
        list: Array
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

    .rotate-n-15 {
        transform: rotate(-15deg);
    }

    .fa-laugh-wink:before{content:"\f59c"}

    .d-flex {
        display: flex !important;
    }

    .sidebar {
        width: 6.5rem;
        min-height: 100vh;
    }

    .sidebar-brand-text {
        display: inline;
    }

    .justify-content-center {
        justify-content: center !important;
    }

    .bg-gradient-primary {
        background-color: #4e73df;
        background-image: linear-gradient(180deg, #4e73df 10%, #224abe 100%);
        background-size: cover;
    }

    .sidebar-brand {
        height: 4.375rem;
        text-decoration: none;
        font-size: 1rem;
        font-weight: 800;
        padding: 1.5rem 1rem;
        text-align: center;
        text-transform: uppercase;
        letter-spacing: 0.05rem;
        z-index: 1;
    }

    .left-side-bar {
        display: flex;
        flex-direction: column;
        padding-left: 0;
        margin-bottom: 0;
        list-style: none;
    }
</style>
